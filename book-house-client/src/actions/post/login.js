import axios from "axios";
import users from "./data.json";

export const login = async (email, password) => {
	try {
		console.log(email, password);
		const userFound = users.find(
			(user) => user.email === email && user.password === password
		);
		console.log(userFound);
		if (userFound) {
			console.log("mmmmmmmmmm");
			// Simulate a delay to mimic network latency
			await new Promise((resolve) => setTimeout(resolve, 1000));

			// Simulate a successful login response
			const mockResponse = {
				data: {
					access_token: userFound,
					message_response: {
						status_response: true,
					},
				},
			};

			return mockResponse;
		} else {
			throw new Error("Invalid email or password");
		}
	} catch (error) {
		throw error;
	}
};
