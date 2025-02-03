def publishDockerImage(String imageName, String tag) {
    def dockerHubUsername = "ngwehtun"
    def credentialsId = "hiki-docker-cred"
    def fullImageName = "${dockerHubUsername}/${imageName}:${tag}"

    withDockerRegistry([credentialsId: credentialsId]) {
        sh "docker push ${fullImageName}"
    }

    sh "docker rmi ${fullImageName}"
}

return this