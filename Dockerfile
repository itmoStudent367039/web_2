FROM quay.io/wildfly/wildfly
ADD ./target/web_2-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/