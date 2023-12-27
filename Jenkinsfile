pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'gym_image'
        GITHUB_CREDENTIALS = credentials('gym-git-credentials')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/sajitkhadka/gym.git']]])
            }
        }

        stage('Build and Package Server') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'GYM_DB_USERNAME', variable: 'DB_USERNAME'),
                                     string(credentialsId: 'GYM_DB_URL', variable: 'DB_URL'),
                                     string(credentialsId: 'GYM_DB_PASSWORD', variable: 'DB_PASSWORD')]) {
                        sh 'cd server && ./mvnw clean install'
                    }
                }
            }
        }

        // stage('Build and Package Next.js App') {
        //     steps {
        //         script {
        //             withNodeJS {
        //                 sh 'cd client && npm install && npm run build'
        //             }
        //         }
        //     }
        // }

        stage('Build server docker') {
            steps {
                script {
                    sh 'docker build -t ${DOCKER_IMAGE} ./server'
                }
            }
        }

        stage('Deploy Docker Container') {
            steps {
                script {
                    sh "docker ps -a -q --filter name=sajit_gym | xargs docker stop || true"
                    sh "docker ps -a -q --filter name=sajit_gym | xargs docker rm || true"

                    withCredentials([string(credentialsId: 'GYM_DB_USERNAME', variable: 'DB_USERNAME'),
                                     string(credentialsId: 'GYM_DB_URL', variable: 'DB_URL'),
                                     string(credentialsId: 'GYM_DB_PASSWORD', variable: 'DB_PASSWORD')]) {
                        docker.withRun("${env.DOCKER_IMAGE}:${BUILD_ID}", "--name sajit_gym -p 8082:8081 -e DB_USERNAME=${DB_USERNAME} -e DB_PASSWORD=${DB_PASSWORD} -e DB_URL=${DB_URL} -d") {
                            // Your container run steps here
                        }
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded! Deploy your Docker container.'
        }

        failure {
            echo 'Pipeline failed!'
        }
    }
}
