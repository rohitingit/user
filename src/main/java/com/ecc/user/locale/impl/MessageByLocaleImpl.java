package com.ecc.user.locale.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.ecc.user.locale.MessageByLocale;


/**
 * @author ROHIT
 *
 */
@Service("messageByLocale")
public class MessageByLocaleImpl implements MessageByLocale {

	@Autowired
	private MessageSource messageSource;

	@Override
	public String getMessage(String id) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(id, null, locale);
	}

	@Override
	public String getMessage(String id, String... params) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(id, params, locale);
	}
}
