package pl.com.softproject.diabetyk.core.model;

import org.hibernate.validator.constraints.Length;

import pl.com.softproject.diabetyk.core.enums.GiodoOperationType;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Class GiodoLog
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Entity
@Table(name = "giodolog", schema = "public")
@SuppressWarnings("PersistenceUnitPresent")
public class GiodoLog extends BaseEntity {

    @NotNull
    private Date logTime;

    @ManyToOne
    private UserData user;

    @Enumerated(EnumType.STRING)
    private GiodoOperationType operation;

    @Length(max = 512)
    private String message;

    public GiodoLog() {

    }

    public GiodoLog(Long id) {

        super(id);
    }

    public GiodoLog(Date logTime, UserData user, GiodoOperationType operation, String message) {

        this.logTime = logTime;
        this.user = user;
        this.operation = operation;
        this.message = message;
    }

    public GiodoLog(Long id, Date logTime, UserData user, GiodoOperationType operation,
                    String message) {

        super(id);
        this.logTime = logTime;
        this.user = user;
        this.operation = operation;
        this.message = message;
    }

    public Date getLogTime() {

        return logTime;
    }

    public void setLogTime(Date logTime) {

        this.logTime = logTime;
    }

    public UserData getUser() {

        return user;
    }

    public void setUser(UserData user) {

        this.user = user;
    }

    public GiodoOperationType getOperation() {

        return operation;
    }

    public void setOperation(GiodoOperationType operation) {

        this.operation = operation;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }
}
