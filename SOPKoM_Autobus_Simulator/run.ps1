function Color-Console {
	$Host.ui.rawui.backgroundcolor = "Black"
	$Host.ui.rawui.foregroundcolor = "Green"
	$hosttime = (Get-ChildItem -Path  $PSHOME\\PowerShell.exe).CreationTime
	$Host.UI.RawUI.WindowTitle  =  "PowerShell ($hosttime)"
   
	Clear-Host
}

Color-Console

java -jar .\demo-0.0.1-SNAPSHOT.jar