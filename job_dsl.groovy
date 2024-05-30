folder('Tools') { // Creates a folder named 'Tools'
    description('Folder for miscellaneous tools.') // Sets the description for the folder
}

freeStyleJob('Tools/clone-repository') { // Creates a freestyle job named 'clone-repository' inside the 'Tools' folder
    parameters { // Defines the parameters for the job
        stringParam('GIT_REPOSITORY_URL', null, 'Git URL of the repository to clone') // Defines a string parameter named 'GIT_REPOSITORY_URL'
    }
    steps { // Defines the steps for the job
        shell('git clone ${GIT_REPOSITORY_URL}') // Executes a shell command to clone the specified Git repository
    }
    wrappers { // Defines the wrappers for the job
        preBuildCleanup() // Adds a pre-build cleanup wrapper
    }
}

freeStyleJob('Tools/SEED') { // Creates a freestyle job named 'SEED' inside the 'Tools' folder
    parameters { // Defines the parameters for the job
        stringParam('GITHUB_NAME', null, 'GitHub repository owner/repo_name (e.g.: "EpitechIT31000/chocolatine")') // Defines a string parameter named 'GITHUB_NAME'
        stringParam('DISPLAY_NAME', null, 'Display name for the job') // Defines a string parameter named 'DISPLAY_NAME'
    }
    steps { // Defines the steps for the job
        dsl { // Executes a DSL script
            text('''job("$DISPLAY_NAME") {
    wrappers {
        preBuildCleanup()
    }
    scm {
        github("$GITHUB_NAME")
    }
    triggers {
        scm('* * * * *')
    }
    steps {
        shell('make fclean')
        shell('make')
        shell('make tests_run')
        shell('make clean')
    }
}'''.stripIndent())
        }
    }
}