import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class TextBlockTester {

  @Test
  void SQL_Test() {
    Long id = 10L;
    String name = "kim";

    Object[] params = new Object[] { id, name };

    String sql = """
      SELECT
          ID,
          NAME,
          CREATED_AT
        FROM POST
        WHERE
            ID = %s
        AND NAME = %s
      """
      .formatted(params);

    assertThat(sql).contains(String.valueOf(id));
    assertThat(sql).contains(name);
  }


  @Test
  void HTML_test() {
    String name = "DENEB";

    String template =
      """
      <html>
        <head>
          <title>Hello</title>
        </head>
        <body>
          <h2>Welcome to my site</h2>
          <p id="name">%s</p> 
        </body>
      </html>
      """
        .formatted(name);

    Document document = Jsoup.parse(template);

    String val = document.getElementById("name").text();

    assertThat(val).isEqualTo(name);
  }

  @Test
  void ESCAPE_Test() {
    String text = """
        Hello, This is Deneb
        I using Java 17 new feature 'text block'
        \t It will be useful for coding
        \n\n
        Best Regards "deneb"
      """;

  }


}