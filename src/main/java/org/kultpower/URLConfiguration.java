package org.kultpower;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sebastian on 08.02.16.
 */
@Component
public class URLConfiguration {

	enum URLS {
		ZEITSCHRIFTEN("/zeitschriften")
		,ZEITSCHRIFTEN_AUSGABE("/zeitschriften/ausgabe")
		,INTERVIEWS("/interviews")
		,HOME("/")
		,PROFIL("/profil")
		;
		public final String value;
		URLS(String value) {
			this.value = value;
		}
	}

	public List<URLS> urls = new ArrayList<>();

	public List<URLS> getUrls() {
		if (urls.size() == 0) {
			synchronized (this) {
				if (urls.size() == 0) {
					urls.addAll(Arrays.asList(URLS.values()));
				}
			}
		}
		return urls;
	}

	public static String getBaseUrl() {
		return "http://www.kultpower.org/";
	}
}
