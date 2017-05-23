Dim fs
Dim folder
Dim path

Set fs = CreateObject("Scripting.FileSystemObject")

classpath = "classes"

Set currentFolder = fs.GetFolder(".")
For Each folder In currentFolder.SubFolders
    If folder.Name = "lib" Then
        For Each file In folder.Files
            classpath = classpath & ";lib\" & file.Name
        Next
    End If
Next

Set args = WScript.Arguments
command = ""
For i = 1 to args.Count - 1
   command = command & " " & args(i)
Next
command = "java -Dlog4j.configuration=" & WScript.Arguments(0) & " -Xmx256M -classpath """ & classpath & """" & command

dateTime = Date & " " & Time
WScript.Echo(dateTime)
WScript.StdErr.WriteLine(dateTime)

Set wshShell = CreateObject("WScript.Shell")
Set WshSysEnv = WshShell.Environment("SYSTEM")

command = WshSysEnv("JAVA_HOME") & "\bin\" & command
WScript.Echo(command)

Set objWshScriptExec = wshShell.Exec (command)
Set objStdOut = objWshScriptExec.StdOut
strOutput = objStdOut.ReadAll
WScript.Echo strOutput
