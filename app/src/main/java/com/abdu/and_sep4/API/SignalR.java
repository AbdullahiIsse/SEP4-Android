package com.abdu.and_sep4.API;

import okhttp3.internal.platform.Platform;

public class SignalR {


/*    //SERVER
    Platform.loadPlatformComponent(new AndroidPlatformComponent());
    mHubConnection =new HubConnection(HOST,"",true,null);
    mHubProxy =mHubConnection.createHubProxy(HUB_NAME);
    ClientTransport clientTransport = new ServerSentEventsTransport(mHubConnection.getLogger());
    SignalRFuture<Void> awaitConnection = mHubConnection.start(clientTransport);
    awaitConnection.done(new Action<Void>() {
        @Override
        public void run (Void obj) throws Exception {
        // Hub Connected
    }
    }).onError(new ErrorCallback() {
        @Override
        public void onError (Throwable error){
            // Connection Error
        }
    }).onCancelled(new Runnable() {
        @Override
        public void run () {
            // Connection Canceled
        }
    });


mHubProxy.on(METHOD_NAME,new SubscriptionHandler1<String>()

    {
        @Override
        public void run (String param1){
        // action to perform
    }
    },String .class);

mHubProxy.on(METHOD_NAME,new SubscriptionHandler3<String, String, String>()

    {
        @Override
        public void run (String param1.String param2.String param3){
        // action to perform
    }
    },String .class,String .class.String .class);


mHubProxy.invoke("Send",name,message,time);

    Logger logger = new Logger() {

        @Override
        public void log(String message, Loglevel) {
            Log.d(Tag, message);
        }
    };

    mHubConnection =new

    HubConnection(HOST, "",true,logger);*/


}
