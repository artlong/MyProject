import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;


public class HelloFreemarker {
	private static Logger logger = Logger.getLogger(HelloFreemarker.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("~~~~ HelloFreemarker ~~~~");
		try {
			Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			cfg.setDirectoryForTemplateLoading(new File("./xml"));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			Template temp = cfg.getTemplate("Qoo.doc");

			try {
				File file = new File("./output", "Qoo2.doc");
				Writer out = new OutputStreamWriter(new FileOutputStream(file));

				Map data = new HashMap();
				data.put("name", "Longan");
//				Map data2 = new HashMap();
//				data2.put("sex", "M");
//				data2.put("birthday", "1979-12-31");
//				data.put("data2", data2);
				temp.process(data, out);
				out.flush();
				out.close();
			} catch (Exception e) {
				logger.error(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
