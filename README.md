# 逻辑梳理
NettyServerStarter实现了ApplicationRunner，项目启动时自动执行run()。

# 新增端口开发
新建一个类实现ProtocolInitialize接口，指定端口和处理类。
