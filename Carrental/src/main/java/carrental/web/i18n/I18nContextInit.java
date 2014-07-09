package carrental.web.i18n;

import static carrental.web.tags.SetLocale.DEFAULT_L10N_BUNDLE_PROVIDER_NAME;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import carrental.web.i18n.impl.TrivialL10nBundleProvider;

@WebListener
public class I18nContextInit implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			servletContextEvent.getServletContext().setAttribute(DEFAULT_L10N_BUNDLE_PROVIDER_NAME, new TrivialL10nBundleProvider());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
//		PropertyConfigurator.configure(FileLoader.getResourceAsStream("log4j.properties"));
	}

}
