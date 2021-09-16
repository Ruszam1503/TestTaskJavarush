package com.game.specification;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;


public class PlayerSpecification {
    public static Specification<Player> findPlayerByName(final String name) {
        return new Specification<Player>() {
            @Override
            public Predicate toPredicate(Root<Player> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                if ( name != null) {
                Predicate likePredicate = criteriaBuilder.like(root.get("name"), "%"+name+"%");
                return likePredicate;
            }
                return null;
        }
    };

}

    public static Specification<Player> findPlayerByTitle(final String title) {
        return new Specification<Player>() {
            @Override
            public Predicate toPredicate(Root<Player> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                if ( title != null) {
                    Predicate likePredicate = criteriaBuilder.like(root.get("title"), "%"+title+"%");
                return likePredicate;
            }
                return null;
        }
    };

}

    public static Specification<Player> findPlayerByDate(final Long after, final Long before) {
        return new Specification<Player>() {
            @Override
            public Predicate toPredicate(Root<Player> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {

                Predicate greater = null;
                Predicate lesser = null;

                if (after == null && before != null) {
                    lesser = criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), new Date(before));
                    return lesser;
                }
                if (after != null && before == null) {
                    greater = criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), new Date(after));
                    return greater;
                }

                if (after != null && before != null) {
                    greater = criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), new Date(after));
                    lesser = criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), new Date(before));
                    return criteriaBuilder.and(greater, lesser);
                }

                return null;
            }
        };
    }


    public static Specification<Player> findPlayerByExperience(final Integer minExperience, final Integer maxExperience) {
        return new Specification<Player>() {
            @Override
            public Predicate toPredicate(Root<Player> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {

                Predicate greater = null;
                Predicate lesser = null;

                if (minExperience == null && maxExperience != null) {
                    lesser = criteriaBuilder.lessThanOrEqualTo(root.get("experience"), maxExperience);
                    return lesser;
                }
                if (minExperience != null && maxExperience == null) {
                    greater = criteriaBuilder.greaterThanOrEqualTo(root.get("experience"), minExperience);
                    return greater;
                }

                if (minExperience != null && maxExperience != null) {
                    greater = criteriaBuilder.greaterThanOrEqualTo(root.get("experience"), minExperience);
                    lesser = criteriaBuilder.lessThanOrEqualTo(root.get("experience"), maxExperience);
                    return criteriaBuilder.and(greater, lesser);
                }

                return null;
            }
        };
    }


    public static Specification<Player> findPlayerByLevel(final Integer minLevel, final Integer maxLevel) {
        return new Specification<Player>() {
            @Override
            public Predicate toPredicate(Root<Player> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {

                Predicate greater = null;
                Predicate lesser = null;

                if (minLevel == null && maxLevel != null) {
                    lesser = criteriaBuilder.lessThanOrEqualTo(root.get("level"), maxLevel);
                    return lesser;
                }
                if (minLevel != null && maxLevel == null) {
                    greater = criteriaBuilder.greaterThanOrEqualTo(root.get("level"), minLevel);
                    return greater;
                }

                if (minLevel != null && maxLevel != null) {
                    greater = criteriaBuilder.greaterThanOrEqualTo(root.get("level"), minLevel);
                    lesser = criteriaBuilder.lessThanOrEqualTo(root.get("level"), maxLevel);
                    return criteriaBuilder.and(greater, lesser);
                }

                return null;
            }
        };
    }

    public static Specification<Player> findPlayerByRace(final Race race) {
        return new Specification<Player>() {
            @Override
            public Predicate toPredicate(Root<Player> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                if (race != null) {
                    Predicate equalPredicate = criteriaBuilder.equal(root.get("race"), race);
                    return equalPredicate;
                }
                return null;
            }
            };

        }

    public static Specification<Player> findPlayerByProfession(final Profession profession) {
        return new Specification<Player>() {
            @Override
            public Predicate toPredicate(Root<Player> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                if (profession != null) {
                Predicate equalPredicate = criteriaBuilder.equal(root.get("profession"), profession);
                return equalPredicate;
            }
                return null;
        }
    };

}

    public static Specification<Player> findPlayerByBanned(final Boolean banned) {
        return new Specification<Player>() {
            @Override
            public Predicate toPredicate(Root<Player> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                if (banned != null) {
                Predicate equalPredicate = criteriaBuilder.equal(root.get("banned"), banned);
                return equalPredicate;
            }
                return null;
        }
    };

}
}
