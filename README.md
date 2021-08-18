
### 自定义spring boot中间件

原理： 
SPI(Service Provider Interface)机制。
1. spring boot启动时回去依赖的starter包resources/META-INF/spring.factories文件加载AutoConfig类。
2. 然后通过@Conditional配置加载条件。