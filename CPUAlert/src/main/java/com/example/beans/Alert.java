package com.example.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="alert")
public class Alert {
       
       @Id
       @Column(name="ebu_nbr")
       private int ebuId;
       
       @Column(name="country_code")
       private String countryCode;
       
       @Column(name="alert_type_cd")
       private int alertType;
       
       @Column(name="alert_status_cd")
       private int alertStatus;

       @Column(name="last_alert_ts_gmt")
       private Date lastAlertGmt;
       
       @Column(name="last_alert_ts_ltz")
       private Date lastAlertLtz;
       
	    public Alert() {
	           super();
	    }
       
	    public Alert(int ebuId, String countryCode, int alertType, int alertStatus, Date lastAlertGmt, Date lastAlertLtz) {
	           super();
	           this.ebuId = ebuId;
	           this.countryCode = countryCode;
	           this.alertType = alertType;
	           this.alertStatus = alertStatus;
	           this.lastAlertGmt = lastAlertGmt;
	           this.lastAlertLtz = lastAlertLtz;
	   }

       public int getEbuId() {
              return ebuId;
       }

       public void setEbuId(int ebuId) {
              this.ebuId = ebuId;
       }

       public String getCountryCode() {
              return countryCode;
       }

       public void setCountryCode(String countryCode) {
              this.countryCode = countryCode;
       }

       public int getAlertType() {
              return alertType;
       }

       public void setAlertType(int alertType) {
              this.alertType = alertType;
       }

       public int getAlertStatus() {
              return alertStatus;
       }

       public void setAlertStatus(int alertStatus) {
              this.alertStatus = alertStatus;
       }

       public Date getLastAlertGmt() {
              return lastAlertGmt;
       }

       public void setLastAlertGmt(Date lastAlertGmt) {
              this.lastAlertGmt = lastAlertGmt;
       }

       public Date getLastAlertLtz() {
              return lastAlertLtz;
       }

       public void setLastAlertLtz(Date lastAlertLtz) {
              this.lastAlertLtz = lastAlertLtz;
       }

       @Override
       public int hashCode() {
              final int prime = 31;
              int result = 1;
              result = prime * result + alertStatus;
              result = prime * result + alertType;
              result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
              result = prime * result + ebuId;
              result = prime * result + ((lastAlertGmt == null) ? 0 : lastAlertGmt.hashCode());
              result = prime * result + ((lastAlertLtz == null) ? 0 : lastAlertLtz.hashCode());
              return result;
       }

       @Override
       public boolean equals(Object obj) {
              if (this == obj)
                     return true;
              if (obj == null)
                     return false;
              if (getClass() != obj.getClass())
                     return false;
              Alert other = (Alert) obj;
              if (alertStatus != other.alertStatus)
                     return false;
              if (alertType != other.alertType)
                     return false;
              if (countryCode == null) {
                     if (other.countryCode != null)
                           return false;
              } else if (!countryCode.equals(other.countryCode))
                     return false;
              if (ebuId != other.ebuId)
                     return false;
              if (lastAlertGmt == null) {
                     if (other.lastAlertGmt != null)
                           return false;
              } else if (!lastAlertGmt.equals(other.lastAlertGmt))
                     return false;
              if (lastAlertLtz == null) {
                     if (other.lastAlertLtz != null)
                           return false;
              } else if (!lastAlertLtz.equals(other.lastAlertLtz))
                     return false;
              return true;
       }

	@Override
	public String toString() {
		return "Alert [ebuId=" + ebuId + ", countryCode=" + countryCode + ", alertType=" + alertType + ", alertStatus="
				+ alertStatus + ", lastAlertGmt=" + lastAlertGmt + ", lastAlertLtz=" + lastAlertLtz + "]";
	}

}

