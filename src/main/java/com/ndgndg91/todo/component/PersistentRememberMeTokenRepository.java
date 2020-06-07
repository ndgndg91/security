package com.ndgndg91.todo.component;

import com.ndgndg91.todo.user.model.RememberMeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class PersistentRememberMeTokenRepository implements PersistentTokenRepository {

    private final EntityManager em;

    @Override
    @Transactional
    public void createNewToken(PersistentRememberMeToken token) {
        RememberMeToken rememberMeToken = RememberMeToken.builder()
                .username(token.getUsername())
                .series(token.getSeries())
                .token(token.getTokenValue())
                .lastUsed(token.getDate())
                .build();
        em.persist(rememberMeToken);
    }

    @Override
    @Transactional
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        RememberMeToken rememberMeToken = null;
        try {
             rememberMeToken = em.createQuery("SELECT rmt FROM RememberMeToken rmt WHERE rmt.series = :series", RememberMeToken.class)
                    .setParameter("series", series)
                    .getSingleResult();
        } catch (NoResultException e) {
            log.info(e.toString());
        }

        if (Objects.isNull(rememberMeToken)) {
            return;
        }

        rememberMeToken.updateToken(series, tokenValue, lastUsed);
        em.merge(rememberMeToken);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        RememberMeToken rememberMeToken = em.createQuery("SELECT rmt FROM RememberMeToken rmt WHERE rmt.series = :series", RememberMeToken.class)
                .setParameter("series", seriesId)
                .getSingleResult();

        return new PersistentRememberMeToken(
                rememberMeToken.getUsername(),
                rememberMeToken.getSeries(),
                rememberMeToken.getToken(),
                rememberMeToken.getLastUsed()
        );
    }

    @Override
    @Transactional
    public void removeUserTokens(String username) {
        em.createQuery("DELETE FROM RememberMeToken rmt WHERE rmt.username =:username")
                .setParameter("username", username)
                .executeUpdate();
    }
}
