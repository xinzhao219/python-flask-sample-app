package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'Deploy'
in the root project, and delete the patch script.
*/
create(DslContext.projectId, BuildType({
    id("Deploy")
    name = "Deploy"

    params {
        param("artifactory_username", "gavin.tu@gmail.com")
        password("artifactory_password", "credentialsJSON:914ce635-1fcd-4b78-a005-c69e57217036", display = ParameterDisplay.HIDDEN)
    }

    steps {
        script {
            name = "DeployToK8s"
            scriptContent = """
                helm repo add helm-demo https://freefox1.jfrog.io/artifactory/helm --username "%artifactory_username%" --password "%artifactory_password%"
                helm repo update
                helm upgrade --install python-flask-sample-app helm-demo/python-flask-sample-app
            """.trimIndent()
            dockerImage = "freefox1.jfrog.io/docker-devops/deploytok8s:1.0.0"
        }
    }
}))

