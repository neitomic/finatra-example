namespace java sdn.finatra.domain.thrift.java
#@namespace scala sdn.finatra.domain.thrift.scala
struct TLocation{
    1: double lat
    2: double long
}
struct TTweetRequest {
  1: string id
  2: string message
  3: optional TLocation location
  4: optional bool sensitive
}
struct TTweetResponse {
  1: string id
  2: string message
  3: optional TLocation location
  4: optional bool sensitive
}

service TTweetService {
  TTweetResponse addTweet(1: required TTweetRequest request)
}
