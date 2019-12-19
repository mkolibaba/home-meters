Switch ($args[0]) {
    'install' {
        ForEach ($command in 'npm install', 'lein deps') {
            iex $command
        }
    }
    'build' {
        # add sync between project.clj and package.json
        ForEach ($command in 'npm install', 'npm run build', 'lein ring server') {
            iex $command
        }
    }
} 

