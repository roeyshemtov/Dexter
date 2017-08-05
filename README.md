# Dexter
===============Work In Progress==============

Dexter Is a Project built up on the top of cloudera Impala.

Dexter provides the following options:
* Killing impala queries by metrics (e.g duration > 1h, hdfs_bytes_read >= 1T)
* Save those queries metadata in some database
  * file
  * JDBC supporter database
  * None - Dont save the queries metadata

# Requierments
* Cloudera Distirbution for Hadoop
  * All CDH versions are supported
* Cloudera Manager
  * All CM versions are supported
* Impala Installed within the CDH
  * All impala versions are supported
  
  
# How To Start?
It simple, you should re-configure those options in application.conf:
  * Cloudera Manager host and port
    * Cluster Name
      * Impala Service Name
  * Dexter Database options (JDBC,File or None)
  * Metrics - which metrics should dexter kill
  
