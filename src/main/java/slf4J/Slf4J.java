package slf4J;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4J {
    final static Logger logger = LoggerFactory.getLogger(Slf4J.class);
    public static void main(final String[] args) {
         logger.info("Entering application.");
         logger.info("Exiting application.");
         logger.debug("this debug log hon.");
         logger.error("this debug log _class.");
    }

}
