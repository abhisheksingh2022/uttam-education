Package: view
Usage: Any view related DTO which has struts dependency. It is preferable to use the dto from model directly. But changes cannot be made to model package to support view, since model is generated from the JSON schema.
If such changes are unavoidable, a wrapper view class should be created encapsulating the underlying model.
