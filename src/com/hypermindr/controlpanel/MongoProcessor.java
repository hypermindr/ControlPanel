package com.hypermindr.controlpanel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.faces.model.SelectItem;

import org.jongo.Jongo;

import com.hypermindr.controlpanel.bean.GlobalDatasourceSelector;
import com.hypermindr.controlpanel.bean.HolderBean;
import com.hypermindr.controlpanel.bean.LogDetail;
import com.hypermindr.controlpanel.bean.LogResult;
import com.hypermindr.controlpanel.entities.Tailer;
import com.hypermindr.controlpanel.entities.materialized.SecurityBean;
import com.hypermindr.controlpanel.web.facade.ControlPanelFacade;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class MongoProcessor extends SecurityBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 942190345392055493L;

	private Mongo mongo;

	private static final String ALL_ITENS = "Todos";

	private static Set<String> methods = new TreeSet<String>();

	private static ArrayList<SelectItem> servers = new ArrayList<SelectItem>();

	private static ArrayList<String> serversStr = new ArrayList<String>();

	private static ArrayList<SelectItem> intervals = new ArrayList<SelectItem>();

	private static ArrayList<SelectItem> numLines = new ArrayList<SelectItem>();

	public String currentInterval;

	public String currentEndpoint;

	public String currentServer;

	public String currentNumLines;

	public String beginDate;

	public String endDate;

	public boolean useTimeSlice;

	public static String S_beginDate;

	public static String S_endDate;

	public static String ScurrentInterval;

	public static String ScurrentEndpoint;

	public static String ScurrentServer;

	public static String ScurrentNumLines;
	
	public static boolean S_timeSlice;

	static {
		intervals.add(new SelectItem("0.25", "15 segundos"));
		intervals.add(new SelectItem("0.5", "30 segundos"));
		intervals.add(new SelectItem("1", "1 minuto"));
		intervals.add(new SelectItem("3", "3 minutos"));
		intervals.add(new SelectItem("5", "5 minutos"));
		intervals.add(new SelectItem("15", "15 minutos"));
		intervals.add(new SelectItem("30", "30 minutos"));
		intervals.add(new SelectItem("60", "1 hora"));
		intervals.add(new SelectItem("120", "2 horas"));
		intervals.add(new SelectItem("180", "3 horas"));
		intervals.add(new SelectItem("360", "6 horas"));
		intervals.add(new SelectItem("720", "12 horas"));

		numLines.add(new SelectItem("-", "-"));
		numLines.add(new SelectItem("1", "1"));
		numLines.add(new SelectItem("2", "2"));
		numLines.add(new SelectItem("3", "3"));
		numLines.add(new SelectItem("4", "4"));
		numLines.add(new SelectItem("5", "5"));
		numLines.add(new SelectItem("6", "6"));
		numLines.add(new SelectItem("7", "7"));
		numLines.add(new SelectItem("8", "8"));
		servers.add(new SelectItem(ALL_ITENS, ALL_ITENS));

	}

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat formatterNoSec = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	// For deviation
	double timeSum = 0.0;
	double deviation = 0.0;

	public MongoProcessor() {

	}

	private synchronized void openMongo() {

		try {
			String mongoServer = GlobalDatasourceSelector.getDatasource();
			MongoOptions mo = new MongoOptions();
			ServerAddress mongoAddress = new ServerAddress(mongoServer, 27017);
			mo.alwaysUseMBeans = true;
			mongo = new Mongo(mongoAddress, mo);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			throw new RuntimeException("Server not responding", e);
		}
	}

	public Hashtable<Integer, TreeMap<Long, HolderBean>> getData() {

		methods.clear();
		Hashtable<Integer, TreeMap<Long, HolderBean>> dataTable = new Hashtable<Integer, TreeMap<Long, HolderBean>>();
		String tag = Long.toString(System.currentTimeMillis());
		TreeMap<Long, HolderBean> data = new TreeMap<Long, HolderBean>();
		if ((currentInterval == null || currentInterval == "")
				&& ScurrentInterval == null) {
			currentInterval = "0.25";
		} else {
			currentInterval = ScurrentInterval;
		}
		if (ScurrentEndpoint == null) {
			ScurrentEndpoint = ControlPanelFacade.getInstance()
					.buscaEndpoints().get(0).getCname();
		}
		if (ScurrentEndpoint != null && ScurrentEndpoint.length() > 0) {
			currentEndpoint = ScurrentEndpoint;
		}
		double timeFrame = 0;
		if (currentInterval != null && currentInterval.length() > 0) {
			timeFrame = System.currentTimeMillis()
					- (1000 * 60 * (1 * Double.parseDouble(currentInterval
							.trim())));
		} else {
			timeFrame = System.currentTimeMillis() - (1000 * 60 * 0.25);
		}
		try {

			long endTime = System.currentTimeMillis();
			long _timeFrame = 0;

			try {
				if (S_timeSlice == true) {
					if (S_beginDate != null && S_beginDate.length() > 0) {
						if (S_endDate != null && S_endDate.length() > 0) {
							_timeFrame = formatterNoSec.parse(S_beginDate)
									.getTime();
							endTime = formatterNoSec.parse(S_endDate).getTime();
							timeFrame = _timeFrame;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			openMongo();
			DB db = mongo.getDB("RaaSLogs");
			Jongo jongo = new Jongo(db);

			StringBuilder noSqlCommand = new StringBuilder();
			noSqlCommand.append("{ aggregate: 'RaaSLogsperformance',"
					+ "pipeline: [ ");
			noSqlCommand.append("{" + "    $match: {  timestamp: { $gt: ");
			noSqlCommand.append(new BigDecimal(timeFrame).toPlainString());
			noSqlCommand.append(",$lt: " + endTime + " }}");
			noSqlCommand.append("},");
			noSqlCommand.append("{");
			noSqlCommand.append("    $match: { tracerid: {$ne: null}}");
			noSqlCommand.append("},");
			if (currentServer != null
					&& !currentServer.trim().equals(ALL_ITENS)
					&& !currentServer.trim().equals("")) {
				noSqlCommand.append("{");
				noSqlCommand.append("    $match: { server: '"
						+ currentServer.trim() + "'}");
				noSqlCommand.append("},");
			}
			if (currentEndpoint != null
					&& !currentEndpoint.trim().equals(ALL_ITENS)
					&& !currentEndpoint.trim().equals("")) {
				noSqlCommand.append("{");
				noSqlCommand.append("    $match: { endpoint: '"
						+ currentEndpoint.trim() + "'}");
				noSqlCommand.append("},");
			}
			
			noSqlCommand.append("{");
			noSqlCommand.append("    $limit: 80000");
			noSqlCommand.append("},");
			 
			noSqlCommand.append("{");
			noSqlCommand.append("   $sort: { timestamp: 1 }");
			noSqlCommand.append("}, {");
			noSqlCommand.append("$group: {");
			noSqlCommand.append("  _id: '$tracerid',");
			noSqlCommand.append("   timeelapsed: { $max: '$timeElapsed' },");
			noSqlCommand.append("   maxtimestamp: { $max: '$timestamp' },");
			noSqlCommand.append("   mintimestamp: { $min: '$timestamp' },");
			noSqlCommand.append("   countdocs: { $sum: 1 },");
			noSqlCommand.append("   details: { $push: '$$ROOT' }");
			noSqlCommand.append("   }");
			noSqlCommand.append("}, {");
			noSqlCommand.append("$project: {");
			noSqlCommand.append("   _id: 1,");
			noSqlCommand.append("   maxtimestamp: 1,");
			noSqlCommand.append("   mintimestamp: 1,");
			noSqlCommand.append("   timeelapsed: 1 ,");
			noSqlCommand.append("     countdocs: 1,");
			noSqlCommand.append("      details: 1");
			noSqlCommand.append("   } }, {");
			noSqlCommand
					.append("       $sort: {  mintimestamp: -1 }}, { $out : 'saida"
							+ tag + "' } ],allowDiskUse: true}");
			System.out.println("Start command at: " + new Date());
			System.out.println(noSqlCommand.toString());
			jongo.runCommand(noSqlCommand.toString()).field("result")
					.as(LogResult.class);
			System.out.println("End aggregation at: " + new Date());
			Iterable<LogResult> res = jongo.getCollection("saida" + tag).find()
					.as(LogResult.class);
			System.out.println("End fetch at: " + new Date());

			Date dateCall = null;
			timeSum = 0;

			for (LogResult logResult : res) {
				HolderBean bean;
				TreeMap<String, BigDecimal> map = new TreeMap<String, BigDecimal>();

				// **
				// Cleaning routine - avoid duplicated entries (ugly)
				/*
				 * Object[] logDetailsArray = logResult.getDetails().toArray();
				 * List<Object> list = new ArrayList<Object>(
				 * Arrays.asList(logDetailsArray)); HashSet<LogDetail>
				 * hashSetUnique = new HashSet<LogDetail>(); for (Object obj :
				 * list) { if (obj instanceof LogDetail)
				 * hashSetUnique.add((LogDetail) obj); }
				 * logResult.getDetails().clear();
				 * logResult.getDetails().addAll(hashSetUnique);
				 */
				// **

				if (logResult.get_id() != null
						&& (logResult.getTimeelapsed() != null)) {
					dateCall = new Date(((LogDetail) logResult.getDetails()
							.toArray()[logResult.getDetails().size() - 1])
							.getTimestamp().longValue());

					BigDecimal delta = logResult.getTimeelapsed();
					
					bean = new HolderBean(delta,
							logResult.getLogDetailsString(),
							formatter.format(dateCall));

					data.put(dateCall.getTime(), bean);

					methods.clear();
					for (LogDetail det : logResult.getDetails()) {

						methods.add(det.getFormattedMethod());

						map.put(det.getFormattedMethod(), det.getTimeElapsed());

						if (det.getServer() != null
								&& !serversStr.contains(det.getServer().trim())
								&& det.getServer().trim().length() > 0) {
							servers.add(new SelectItem(det.getServer().trim(),
									det.getServer().trim()));
							serversStr.add(det.getServer().trim());
						}
					}

					bean.setMethodsTime(map);

					data.put(dateCall.getTime(), bean);
					TreeMap<Long, HolderBean> theTreeMap = null;
					try {
						theTreeMap = dataTable
								.get(bean.getMethodsTime().size());
					} catch (IndexOutOfBoundsException ioobe) {
					}

					if (theTreeMap == null) {
						theTreeMap = new TreeMap<Long, HolderBean>();
						theTreeMap.put(dateCall.getTime(), bean);
						dataTable.put(bean.getMethodsTime().size(), theTreeMap);
					} else {
						theTreeMap.put(dateCall.getTime(), bean);
						dataTable.put(bean.getMethodsTime().size(), theTreeMap);
					}

				}
			}

			jongo.getCollection("saida" + tag).drop();
			System.out.println("Droped temp collection: " + new Date());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("End processing at: " + new Date());
			mongo.close();
			S_beginDate = null;
			S_endDate = null;
			S_timeSlice = false;
		}
		
		return dataTable;
	}

	public double deviation(List<LogResult> list) {
		double dev = 0.0;
		if (list.size() == 1) {
			return 0.0;
		} else {
			double media = timeSum;
			double sum = 0l;
			for (int i = 0; i < list.size(); i++) {
				double result = list.get(i).getTimeelapsed().longValue()
						- media;
				sum = sum + result * result;
			}
			dev = Math.sqrt(((double) 1 / (list.size() - 1)) * sum);
		}
		
		return dev;
	}

	public double getDeviation() {
		return deviation;
	}

	public static Set<String> getMethods() {
		return methods;
	}

	public void setMethods(Set<String> methods) {
		this.methods = methods;
	}

	public ArrayList<SelectItem> getIntervals() {
		return intervals;
	}

	public void setIntervals(ArrayList<SelectItem> intervals) {
		this.intervals = intervals;
	}

	public String getCurrentInterval() {
		return currentInterval;
	}

	public void setCurrentInterval(String currentInterval) {
		this.currentInterval = currentInterval;
		ScurrentInterval = currentInterval;
	}

	public String getCurrentEndpoint() {
		return currentEndpoint;
	}

	public void setCurrentEndpoint(String current) {
		currentEndpoint = current;
		ScurrentEndpoint = current;
	}

	public ArrayList<SelectItem> getServers() {
		return servers;
	}

	public void setServers(ArrayList<SelectItem> servers) {
		this.servers = servers;
	}

	public String getCurrentServer() {
		return currentServer;
	}

	public void setCurrentServer(String currentServer) {
		this.currentServer = currentServer;
		ScurrentServer = currentServer;
	}

	public ArrayList<SelectItem> getNumLines() {
		return numLines;
	}

	public void setNumLines(ArrayList<SelectItem> numLines) {
		MongoProcessor.numLines = numLines;
	}

	public String getCurrentNumLines() {
		return currentNumLines;
	}

	public void setCurrentNumLines(String currentNumLines) {
		this.currentNumLines = currentNumLines;
		ScurrentNumLines = currentNumLines;
	}

	public void atualiza() {

	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
		S_beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
		S_endDate = endDate;
	}

	public boolean isUseTimeSlice() {
		return useTimeSlice;
	}

	public void setUseTimeSlice(boolean useTimeSlice) {
		this.useTimeSlice = useTimeSlice;
		S_timeSlice = useTimeSlice;
	}
	
	public List<Tailer> getActiveTailers() {
		
		openMongo();
		DB db = mongo.getDB("RaaSLogs");
		Jongo jongo = new Jongo(db);
		long dt = System.currentTimeMillis() - 3600000;
		StringBuilder noSqlCommand = new StringBuilder();
		noSqlCommand.append("{ aggregate: 'RaaSLogsperformance', pipeline: [ ");
		noSqlCommand.append("{    $match: { timestamp: { $gt: "+dt+" }}},");
		noSqlCommand.append("{    $match: { tracerid: {$ne: null}}},");
		noSqlCommand.append("{   $sort: { timestamp: -1 }},");
		noSqlCommand.append("{$group: {  _id: '$server',   lastEvent: { $max: '$timestamp' }}},");
		noSqlCommand.append("{$project: {   _id: -1,  lastEvent: -1} }]})");
		System.out.println("Start command at: " + new Date());
		System.out.println(noSqlCommand.toString());
		List<Tailer> tailers = jongo.runCommand(noSqlCommand.toString()).field("result")
		.as(Tailer.class);
		mongo.close();
		return tailers;
		
	}

}
