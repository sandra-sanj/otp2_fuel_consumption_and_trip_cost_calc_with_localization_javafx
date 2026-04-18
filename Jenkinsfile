pipeline {
    agent any
    tools {
        maven 'Maven3'
    }

    environment {
        PATH = "/usr/local/bin:/usr/bin:/bin:${env.PATH}"
        SONARQUBE_SERVER = 'SonarQubeServer'  // The name of the SonarQube server configured in Jenkins

        DOCKERHUB_CREDENTIALS_ID = 'docker_hub'
        DOCKER_IMAGE_APP = 'sandrajuu/fuel_cost_calc_with_localization_javafx_app'
        DOCKER_IMAGE_DB = 'sandrajuu/fuel_cost_calc_with_localization_javafx_database'
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/sandra-sanj/otp2_fuel_consumption_and_trip_cost_calc_with_localization_javafx.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn clean install'
                    } else {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubeServer') {
                    sh """
                                /opt/homebrew/opt/sonar-scanner/bin/sonar-scanner \
                                -Dsonar.projectKey=otp2_fuel_trip_cost_calc \
                                -Dsonar.sources=src \
                                -Dsonar.projectName=Fuel_consumption_and_trip_cost_calculator \
                                -Dsonar.coverage.exclusions=**/src/test/java/**,**/HelloController.java,**/HelloApplication.java,**/Launcher.java \
                                -Dsonar.host.url=http://localhost:9000 \
                                -Dsonar.login=${env.SONAR_TOKEN} \
                                -Dsonar.java.binaries=target/classes
                            """
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    if (isUnix()) {
                        sh "docker build -t ${DOCKER_IMAGE_APP}:${DOCKER_TAG} ."
                        sh "docker build -t ${DOCKER_IMAGE_DB}:${DOCKER_TAG} ./database_schema"
                    } else {
                        bat "docker build -t ${DOCKER_IMAGE_APP}:${DOCKER_TAG} ."
                        bat "docker build -t ${DOCKER_IMAGE_DB}:${DOCKER_TAG} ./database_schema"
                    }
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    if (isUnix()) {
                        withCredentials([usernamePassword(credentialsId: 'docker_hub', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                            sh "echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin"
                            sh "docker push ${DOCKER_IMAGE_APP}:${DOCKER_TAG}"
                            sh "docker push ${DOCKER_IMAGE_DB}:${DOCKER_TAG}"
                        }
                    } else {
                        withCredentials([usernamePassword(credentialsId: 'docker_hub', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                            bat "echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin"
                            bat "docker push ${DOCKER_IMAGE_APP}:${DOCKER_TAG}"
                            bat "docker push ${DOCKER_IMAGE_DB}:${DOCKER_TAG}"
                        }
                    }
                }
            }
        }
    }
}