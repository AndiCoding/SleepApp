Domain Layer: This layer contains the business logic of the application. 
It defines the use cases and the core business rules. It is independent of any specific data source or framework. The domain layer typically includes:  
Models: Core business entities.
Repositories: Interfaces that define the operations for data access, which are implemented in the data layer.
Use Cases: Classes that encapsulate a specific business operation or logic.


Data Layer: This layer is responsible for data management. 
It handles data retrieval, storage, and caching. 
The data layer typically includes:  
Models: Data transfer objects (DTOs) or 
entities that represent the data structure.
Repositories: Implementations of the repository interfaces defined in the domain layer. These implementations interact with data sources such as databases, network services, or local storage.
Data Sources: Classes that handle the actual data operations, such as making network requests or querying a database.

You can use data class (if you need to send params), class (if no param is required).

