@echo off
cscript //nologo java_with_classpath_debug.vbs log4j_task.properties com.core.merchant.task.Launcher >> logs\merchant_task.out 2>> logs\merchant_task.err
