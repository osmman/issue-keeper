module qa.tools.ikeeper {
    requires org.slf4j;
    requires gson;

    exports qa.tools.ikeeper;
    exports qa.tools.ikeeper.annotation;
    exports qa.tools.ikeeper.action;
    exports qa.tools.ikeeper.client;
    exports qa.tools.ikeeper.client.connector;
    exports qa.tools.ikeeper.interceptor;
    exports qa.tools.ikeeper.test;
}
