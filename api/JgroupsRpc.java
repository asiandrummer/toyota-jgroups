package cmu.practicum;



import org.jgroups.JChannel;
import org.jgroups.blocks.RequestOptions;
import org.jgroups.blocks.ResponseMode;
import org.jgroups.blocks.RpcDispatcher;
import org.jgroups.util.RspList;



public class JgroupsRpc {
   JChannel           channel;
   public   RpcDispatcher      disp;
   String             props; // set by application

   
   private static JgroupsRpc _instance=null;
   
   
   private JgroupsRpc(){
	   
   }
   
   
   /**
    * @return JgroupsRpc a JgroupsRpc instance
   */
   public synchronized static JgroupsRpc getInstance(){
	   if(_instance==null){
		   _instance=new JgroupsRpc();
		  System.setProperty("java.net.preferIPv4Stack","true");
	   }
	   return _instance;
   }
   
   /**
    * Starts a Jgroups cluster named V2VCloud
    * @throws java.lang.Exception exception
   */
    public void start() throws Exception {
        channel=new JChannel(props);
        channel.setDiscardOwnMessages(true);
        disp=new RpcDispatcher(channel, this);
        channel.connect("V2VCloud");

     
    }
    
    public  <T extends CommonAPI<?>> T callRemote(T appObj) throws Exception {
    	appObj.execute();
    	return appObj;
    }
    
    
    
    /**
     * Allows to query vehicles in the cloud for information . Assumes that all vehicles have the classes and thereby instantiate
     * the objects. The objects attributes are processed via adding code in the execute() method of the object class. For example see VehicleDistance class
     *  @param responseMode   can be different modes such as ResponseMode.GET_ALL, ResponseMode.GET_FIRST or  ResponseMode.GET_MAJORITY
     * @param timeout   time to wait 
     * @param val   the instance of the object
     * @param valType  the classname of the object
     * @param <T>  template  for the object instance
     * @return RspList with objects from different vehicles. See example from SampleApp class for how to parse responseList
     */
    public  <T> RspList<T>  dispatch(ResponseMode responseMode,int timeout,T val, Class<T> valType){
		RspList<T> rsp_list;
	    RequestOptions opts=new RequestOptions(responseMode, timeout);
		try {
			rsp_list=this.disp.callRemoteMethods(null,
			        "callRemote",
			        new Object[]{val},
			        new Class[]{valType},
			        opts);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rsp_list=null;
		}
		return rsp_list;
    }
    
  
}