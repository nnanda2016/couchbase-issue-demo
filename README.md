# couchbase-issue-demo
Demo application to show issue with Couchbase Java SDK and Server incompatibility.

This application generates log files under `build/logs`.

To run the application for error contents, use following command. 

```bash
./gradlew clean build runApp -PwithErr --refresh-dependencies
```
To run the application for valid contents, use following command. 
```bash
./gradlew clean build runApp --refresh-dependencies
```

Both the above commands should print the log file contents in the same command prompt.
