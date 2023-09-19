package com.lld.TicTacToe.bots;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lld.TicTacToe.model.Board;
import com.lld.TicTacToe.model.Cell;
import com.lld.TicTacToe.model.Player;

@Service
public class OpenAIAPIService {

	@Value("${openai.api.key}")
	private String apiKey;

	public Cell getNextMoveContent(Player player, StringBuilder formattedBoard, Board baord) {
		String apiUrl = "https://api.openai.com/v1/chat/completions";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + apiKey);

		StringBuilder sb = new StringBuilder("The below is my tic tac toe board Provide the next move of player '");
		sb.append(player.getSymbol());
		sb.append(
				"' as a bot. The suggestion should aim to either win the game for player 'B' or block the opponent player (player 'A') from winning on their next move. Ensure that the suggested cell is currently unoccupied (not already marked by either player). Give the message only in JSON format with fields 'rowNum' , 'colNum'.\\n");
		sb.append(formattedBoard);

		String requestBody = "{\n" + "  \"model\": \"gpt-3.5-turbo\",\n" + "  \"messages\": [\n" + "    {\n"
				+ "      \"role\": \"system\",\n" + "      \"content\": \"" + sb.toString().replace("\n", "\\n")
				+ "\"\n" + "    }\n" + "  ],\n" + "  \"temperature\": 1.62,\n" + "  \"max_tokens\": 256,\n"
				+ "  \"top_p\": 1,\n" + "  \"frequency_penalty\": 0,\n" + "  \"presence_penalty\": 0\n" + "}";

		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity,
				String.class);
		Cell cell = null;

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			String jsonResponse = responseEntity.getBody();
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode root = objectMapper.readTree(jsonResponse);
				JsonNode contentNode = root.at("/choices/0/message/content");
				JsonNode contentJson = objectMapper.readTree(contentNode.textValue());

				int rowNum = contentJson.get("rowNum").asInt();
				int colNum = contentJson.get("colNum").asInt();
				cell = baord.getCellsList().get(rowNum).get(colNum);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return cell;
	}
}
