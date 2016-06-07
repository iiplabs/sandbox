package com.iiplabs.wsc.util;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;
import com.iiplabs.wsc.model.dao.TokenDao;
import com.iiplabs.wsc.model.dao.UserDao;

public final class SearchCriteriaUtility {

	private SearchCriteriaUtility() {
		throw new AssertionError();
	}
	
	/**
	 * Method searches user with given email and password
	 * @param email
	 * @param password
	 * @return
	 */
	public static Specification<UserDao> findUser(final String email, final String password) {
		return new Specification<UserDao>() {

			@Override
			public Predicate toPredicate(Root<UserDao> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = Lists.newArrayList();
				
				Predicate emailPred = cb.equal(cb.upper(root.<String>get("email")), email.trim().toUpperCase());
				predicates.add(emailPred);

				Predicate passwordPred = cb.equal(root.<String>get("password"), password.trim());
				predicates.add(passwordPred);

				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		};
	}

	/**
	 * Method searches tokens for given email
	 * @param email
	 * @return
	 */
	public static Specification<TokenDao> findUserTokens(final String email) {
		return new Specification<TokenDao>() {
			
			@Override
			public Predicate toPredicate(Root<TokenDao> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(cb.upper(root.<UserDao>get("user").<String>get("email")), email.trim().toUpperCase());
			}
		};		
	}
	
}
