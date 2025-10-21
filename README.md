# AI志愿填报顾问系统 🎓

## 📋 项目简介

基于 **LangChain4j** 和 **阿里云通义千问API** 构建的智能AI志愿填报顾问系统，为高考考生提供专业、个性化的志愿填报咨询服务。系统融合了智能问答、预约服务、知识库检索等核心功能，帮助考生科学规划志愿填报。

## 🔧 技术架构

- **后端框架**: `Spring Boot 3.5.6`
- **AI框架**: `LangChain4j`
- **大语言模型**: 阿里云通义千问 (`qwen-plus`)
- **数据库**: `MySQL` + `Redis`
- **前端技术**: `Vue3` + `TailwindCSS`
- **开发语言**: `Java 17`

## 🚀 核心功能

### 🤖 智能咨询服务
- 🏫 院校信息查询（简介、录取规则、奖学金等）
- 🍽️ 食宿条件与联系方式查询
- 📊 专业录取情况分析
- 🔥 热门专业推荐 & ⚠️ 天坑专业预警
- 🎯 智能志愿推荐（冲稳保策略）

### 📅 预约服务管理
- 📞 一对一志愿填报预约
- 🔍 预约信息实时查询
- 💾 预约数据持久化存储

### 📚 知识库管理
- 📄 PDF文档智能解析
- 🧠 向量数据库存储
- 🔍 语义相似度检索

### 💬 会话管理
- 💰 Redis会话记忆
- ⚡ 流式实时响应
- 🔁 多轮对话支持

## 📁 项目结构

```
src/
├── main/
│   ├── java/com/mwc/langchain4j/
│   │   ├── aiconfig/          # AI配置类
│   │   ├── aiconfig/          # AI服务接口
│   │   ├── controller/        # 控制器层
│   │   ├── mapper/            # 数据访问层
│   │   ├── pojo/              # 实体类
│   │   ├── repository/        # 存储层实现
│   │   ├── service/           # 业务逻辑层
│   │   ├── tools/             # AI工具类
│   │   └── LangChain4jApplication.java  # 启动类
│   └── resources/
│       ├── content/           # 知识库文档
│       ├── static/            # 前端静态资源
│       ├── application.yml    # 配置文件
│       └── system.txt         # AI系统提示词
└── test/                      # 测试代码
```


## 🛠️ 核心组件详解

### 🤖 AI服务组件
- `ConsultantService`: AI顾问服务接口，集成流式聊天功能
- `ReservationTool`: 预约工具类，提供预约和查询功能
- `CommonConfig`: AI配置类，管理模型、记忆、检索器等组件

### 💾 数据存储组件
- `ReservationMapper`: 预约信息数据库操作接口
- `RedisChatMemoryStore`: Redis会话记忆存储实现
- `InMemoryEmbeddingStore`: 内存向量数据库

### 💼 业务组件
- `ReservationService`: 预约业务逻辑接口
- `ReservationServiceImpl`: 预约业务逻辑实现

## ⚙️ 配置说明

### 环境变量
```yaml
DASHSCOPE_API_KEY: 阿里云DashScope API密钥
```


### 数据库配置
- **MySQL**: 存储预约信息
- **Redis**: 存储会话记忆

### AI模型配置
- **对话模型**: `qwen-plus`
- **嵌入模型**: `text-embedding-v4`

## 🚀 部署运行

### 环境要求
1. `JDK 17+`
2. `MySQL 8.0+`
3. `Redis 6.0+`
4. `Maven 3.6+`

### 运行步骤
```bash
# 1. 配置数据库连接信息
# 2. 设置阿里云API密钥环境变量
# 3. 启动Redis服务
# 4. 执行Maven命令
mvn clean install
mvn spring-boot:run
```


## 🌐 API接口

### 聊天接口
```
GET /chat?question={问题}&memoryId={会话ID}
```

- ⚡ 支持流式响应
- 🔗 通过 `memoryId` 维护会话状态

## 💻 前端功能

- 🔄 实时流式对话
- 🌙 暗黑模式切换
- 📚 会话历史管理
- 📱 响应式设计
- ⌨️ 打字机效果

## 🧪 测试用例

包含完整的单元测试:
- `ReservationServiceTest`: 预约服务测试
- `LangChain4jApplicationTests`: 应用启动测试

## ⚠️ 注意事项

1. 🔑 需要有效的阿里云API密钥才能使用AI功能
2. 💾 Redis用于持久化会话记忆，建议配置持久化策略
3. 📚 知识库文档需放置在 `resources/content` 目录下
4. 🎯 系统仅回答高考志愿填报相关问题
