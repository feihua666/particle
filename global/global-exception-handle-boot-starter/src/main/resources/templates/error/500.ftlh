<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>500 - 服务器错误</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
        }
        .container {
            max-width: 600px;
            padding: 40px;
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            border-radius: 20px;
        }
        .error-code {
            font-size: 120px;
            font-weight: 900;
            line-height: 1;
            margin-bottom: 20px;
            text-shadow: 0 10px 20px rgba(0,0,0,0.2);
        }
        .error-title {
            font-size: 28px;
            margin-bottom: 20px;
            font-weight: 600;
        }
        .error-message {
            font-size: 18px;
            margin-bottom: 30px;
            opacity: 0.9;
            line-height: 1.6;
        }

        .icon {
            font-size: 60px;
            margin-bottom: 20px;
            animation: spin 4s linear infinite;
        }
        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="icon">⚙️</div>
    <div class="error-code">500</div>
    <div class="error-title">服务器内部错误</div>
    <div class="error-message">
        服务器遇到了意外错误，无法完成您的请求。<br>
        请稍后再试，或联系技术支持。
    </div>
</div>
<!--500.ftlh in global-exception templates/error-->
</body>
</html>
