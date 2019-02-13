package eu.nouss.hackatonleaderboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Score.
 */
@Entity
@Table(name = "score")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "jhi_value", nullable = false)
    private Integer value;

    @ManyToOne
    @JsonIgnoreProperties("scores")
    private Stage stage;

    @ManyToOne
    @JsonIgnoreProperties("scores")
    private Team team;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public Score value(Integer value) {
        this.value = value;
        return this;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Stage getStage() {
        return stage;
    }

    public Score stage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Team getTeam() {
        return team;
    }

    public Score team(Team team) {
        this.team = team;
        return this;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        if (score.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), score.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Score{" +
            "id=" + getId() +
            ", value=" + getValue() +
            "}";
    }
}
