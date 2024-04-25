package com.mklachl.sopkom.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.mklachl.sopkom.model.entity.User;
import com.mklachl.sopkom.services.UserService;

public final class Helpers {

	

	public static void inject(Model model) {
		 SecurityContext context = SecurityContextHolder.getContext();
		 Authentication auth = context.getAuthentication();
		 model.addAttribute("logged", auth != null && !("anonymousUser").equals(auth.getName()));
		 model.addAttribute("isAmator", auth != null && auth.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_AMATOR")));
		 model.addAttribute("isProfi", auth != null && auth.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_PROFI")));
		 model.addAttribute("isAdmin", auth != null && auth.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN")));

	}
	
	public static User getUserFromContext(UserService service) {
		 SecurityContext context = SecurityContextHolder.getContext();
		 Authentication auth = context.getAuthentication();
		 return service.findUserByLogin(auth.getName());
	       
	}
}
