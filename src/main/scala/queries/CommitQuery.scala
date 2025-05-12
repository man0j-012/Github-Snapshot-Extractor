package queries

import caliban.client.CalibanClientError
import caliban.client.Github._
import org.slf4j.{Logger, LoggerFactory}
import queries.RepoQuery.getClass
import queries.util.HttpUtil.sendRequest
import sttp.capabilities.WebSockets
import sttp.capabilities.zio.ZioStreams
import sttp.client3._
import sttp.client3.httpclient.zio.HttpClientZioBackend
import sttp.model.Header
import zio._

object CommitQuery extends ZIOAppDefault {
  val logger: Logger = LoggerFactory.getLogger(getClass)
  def run: ZIO[Any , Throwable, Unit] = {
    val githubGraphqlEndpoint = uri"https://api.github.com/graphql"
    //    val githubGraphqlEndpoint = uri"${config.getString("github.graphqlEndpoint")}"
    //    val githubOauthToken: String = config.getString("github.oauthToken")

    // Define the GraphQL query to fetch commits for a specific repository
    val commitsQuery =
      Query.
        repository(owner = "0x1DOCD00D", name = "NetGameSim")(
          Repository.name ~
            Repository.defaultBranchRef(caliban.client.Github.Ref.targetOption(onCommit = Option(Commit.history(first = Some(5))(CommitHistoryConnection.
              edges(CommitEdge.node(Commit.oid ~ Commit.message ~ Commit.comments(first = Some(5))(CommitCommentConnection.edges(CommitCommentEdge.node(CommitComment.bodyText)))  ~ Commit.blame(path = "NetModelGenerator/src/main/scala/NetGraphAlgebraDefs/GraphStore.scala")(Blame.
                ranges(BlameRange.startingLine ~ BlameRange.endingLine ~ BlameRange.commit(Commit.author(GitActor.name))) ))))))

//              Commit.message,
//              Commit.oid,
//              Commit.author(Author.name, Author.email, Author.date)
        ))



    val call1 = sendRequest(commitsQuery.toRequest(githubGraphqlEndpoint, useVariables = true))

    val result: ZIO[Any, Throwable, Unit] =
      call1
        .provideLayer(HttpClientZioBackend.layer())
        .flatMap { response =>
          // Handle response here
          println(s"Response: $response")
          ZIO.unit
        }

    result
  }
}
