def checkoutToJenkinsConfRepo() {
    def branch = 'develop'
    def repoUrl = 'https://github.com/ngwehtun-rv/jenkins-conf.git'
    def credentialsId = 'hiki-github-cred'

    checkout([
        $class: 'GitSCM',
        branches: [[name: "*/${branch}"]],
        userRemoteConfigs: [[url: repoUrl, credentialsId: credentialsId]]
    ])
}

def checkoutToXDRRepo(branch, path) {
    def repoUrl = 'https://github.com/ngwehtun-rv/xdr-research.git'
    def credentialsId = 'hiki-github-cred'

    checkout([
        $class: 'GitSCM',
        branches: [[name: "*/${branch}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [
                [$class: 'CloneOption', depth: 1, noTags: true, reference: '', shallow: true, timeout: 30],
                [$class: 'SparseCheckoutPaths', sparseCheckoutPaths: [[path: path]]]
        ],
        submoduleCfg: [],
        userRemoteConfigs: [[
            url: repoUrl,
            credentialsId: 'hiki-github-cred'
        ]]
    ])
}

return [
    xdrRepo: this.&checkoutToXDRRepo,
    confRepo: this.&checkoutToJenkinsConfRepo
]
