package com.nd.idioticspringsecuritydemo.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Shishkov A.V. on 16.08.18.
 */
public enum Role implements GrantedAuthority {
	USER, ADMIN;

	@Override
	public String getAuthority() {
		return name();
	}
}
