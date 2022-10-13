package cm.pak.training.tools;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.OutputStream;

@Component
@Scope("session")
public class SessionFile {
    private OutputStream os ;

    public SessionFile() {
    }

    public OutputStream getOs() {
        return os;
    }

    public void setOs(OutputStream os) {
        this.os = os;
    }
}
