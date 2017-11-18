package ohtu;

import org.junit.rules.ExternalResource;

class ServerRule extends ExternalResource {

    private final int port;

    public ServerRule(int port) {
        this.port = port;
    }

}
