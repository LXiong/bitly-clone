package com.bitly.db;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bitly.model.ShortenLink;
import com.bitly.model.ShortenLinkDb;

@Repository
@Transactional(readOnly = true)
public class ShortenDaoImpl implements ShortenDao {

    @Autowired
    private SessionFactory sessionFactory;
 
    @Override
    public ShortenLink findByShortenUrl(String shortenUrl) {
          final String hql = "FROM ShortenLinkDb WHERE shortenUrl=:shortenUrl";
          
          Session session = sessionFactory.openSession();
          Query query = session.createQuery(hql);
          query.setParameter("shortenUrl", shortenUrl);
          
          return (ShortenLink)query.uniqueResult();
    }
    
    
    @Override
    public ShortenLink findByOriginalUrl(String originalUrl) {
          final String hql = "FROM ShortenLinkDb WHERE originalUrl=:originalUrl";
          
          Session session = sessionFactory.openSession();
          Query query = session.createQuery(hql);
          query.setParameter("originalUrl", originalUrl);
          
          return (ShortenLink)query.uniqueResult();
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertShortenLink(ShortenLink link) {
        Session session = sessionFactory.openSession();
        session.save(new ShortenLinkDb(link));
        
        //TODO: should not flush everytime in production
        session.flush();
    }
    
}