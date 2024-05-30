FROM jenkins/jenkins:lts

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

USER root

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

USER jenkins

COPY my_marvin.yml /var/jenkins_home/casc_configs/my_marvin.yml
COPY job_dsl.groovy /var/jenkins_home/job_dsl.groovy
ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc_configs

EXPOSE 8080