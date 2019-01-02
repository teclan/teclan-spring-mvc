## 项目：Spring-MVC脚手架

>  提供数据库连接和ActiveMQ连接

## 开发人员配置

为避免不同开发人员在不同机器上开发导致的配置文件重复提交或者每次更新代码后都需要重新修改相关配置才能适配自己的测试环境，
需要自己指定自己的配置文件，并且这些配置文件不允许提交至版本库，如果对部署的配置文件需要修改的，需要在项目的公共配置文件（`src/main/resources/properties/*.properties`）做相应的修改并提交。具体的配置为，配置一个名为`YW_ENV`（远
望环境变量）的环境变量，如果是给系统添加环境变量需要重启开发IDE，如果是运行程序时指定环境变量则不需要，如果找不到指定
的环境变量，则使用公共配置。

现对此配置的影响做说明：

例如，有环境变量 `YW_ENV`值为`dev`，**建议使用此环境变量值**，则启动程序的时候会加载 `src/main/resources/properties/dev/*.properties`，

再例如有环境变量 `YW_ENV`值为`product`，建议使用此环境变量值，则启动程序的时候会加载 `src/main/resources/properties/product/*.properties`，

如果环境变量 `YW_ENV`值为空或不存在，则直接加载`src/main/resources/*.properties`。

### xml 配置和读取配置文件的发法。

以下所有的配置示例，均基于环境变量`YW_ENV`

xml文件中有需要指定加载配置文件路径的，示例配置如下:

```

...

<bean id="propertyConfigurer"
		class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="locations">
			<list>
				<value>classpath:/properties/#{systemEnvironment['YW_ENV']}/config.properties</value>
				<value>classpath:/properties/#{systemEnvironment['YW_ENV']}/mysql.properties</value>
				<!-- <value>classpath:/properties/postgreSQL.properties</value> -->
			</list>
		</property>
		<property name="fileEncoding" value="utf-8" />
</bean>

...
```

Java代码中有需要读取配置文件配置的，示例如下：

```
	private static PropertyConfigUtil propertyConfigUtil = PropertyConfigUtil
			.getInstance("config.properties");
	private static int NUM_PER_SHEET = propertyConfigUtil.getIntValue("sheet.num");
	private static int NUM_PER_REQUEST_ES = propertyConfigUtil.getIntValue("selectES.num");
	private static String imageSrvUrl = propertyConfigUtil.getValue("imageSrv.url");
```

加载配置文件的工具类部分代码:

```
     /**
	 * 默认加载 classpath 下 properties 目录中环境变量YW_ENV对应目录下的配置
	 * 
	 * @param propertiesFile
	 *            配置文件名称
	 * @return
	 */
	public static PropertyConfigUtil getInstance(String propertiesFile) {
		PropertyConfigUtil configUtil = (PropertyConfigUtil) propertyConfigUtils
				.get(propertiesFile);
		if (configUtil == null) {
			configUtil = new PropertyConfigUtil(propertiesFile, DEFAULT_PROPERTIES_DIR, DEFAULT_ENV);
			propertyConfigUtils.put(propertiesFile, configUtil);
		}
		return configUtil;
	}

	/**
	 * 加载指定目录下，指定环境变量对应的目录下的某个配置文件
	 * 
	 * @param propertiesFile
	 *            配置文件名称
	 * @param propertiesDir
	 *            配置文件目录
	 * @param env
	 *            环境变量
	 * @return
	 */
	public static PropertyConfigUtil getInstance(String propertiesFile, String propertiesDir,
	 String   env) {
		PropertyConfigUtil configUtil = (PropertyConfigUtil) propertyConfigUtils.get(propertiesFile);
		if (configUtil == null) {
			configUtil = new PropertyConfigUtil(propertiesFile, propertiesDir, env);
			propertyConfigUtils.put(propertiesFile, configUtil);
		}
		return configUtil;
	}
```

__针对的 `src\main\webapp\WEB-INF\web.xm`的自定义配置目前还不支持。__


