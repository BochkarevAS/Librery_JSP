<%@ page import="java.util.Enumeration"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="/js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="/js/jquery.gdocsviewer.js"></script>
    </head>
    <body>

        <div class="pdf_viewer">
        
        <a href="../js/sor.pdf" class="embed">Download file</a>
        <!-- <a href="C:/java/Изучаем C# .pdf" id="embedURL">Download file</a> -->
        
		<script type="text/javascript"> 
			$(document).ready(function() {
				$('a.embed').gdocsViewer({width: 300, height: 750});
				//$('#embedURL').gdocsViewer();
			});
		</script> 
        
        </div>
    </body>
</html>
