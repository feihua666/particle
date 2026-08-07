<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - 页面未找到</title>
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
            animation: float 3s ease-in-out infinite;
        }
        @keyframes float {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-10px); }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="icon">🔍</div>
    <div class="error-code">404</div>
    <div class="error-title">页面未找到</div>
    <div class="error-message">
        您访问的页面不存在或已被移除。<br>
        请检查URL是否正确，确认您访问的页面是否已发布或返回首页。
    </div>
</div>
<!--404.ftlh in cms-adapter templates/cms/global-->
</body>
</html>
