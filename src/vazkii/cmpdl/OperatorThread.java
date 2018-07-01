package vazkii.cmpdl;

public class OperatorThread extends Thread {

	String url;
	String version;

	public OperatorThread(String url, String version) {
		this.url = url;
		this.version = version;
		setName("Operator");
		setDaemon(true);
		start();
	}

	public void stopOp(){
		CMPDL.stop();
	}

	@Override
	public void run() {
		try {
			CMPDL.downloadFromURL(url, version);
		} catch(Exception ex) {
			ex.printStackTrace();
			Interface.addLogLine("Error: " + ex.getClass().toString() + ": " + ex.getLocalizedMessage());
			for(StackTraceElement e : ex.getStackTrace())
				Interface.addLogLine(e.toString());
			Interface.error();
		}

		Interface.operatorThread = null;
	}

}
