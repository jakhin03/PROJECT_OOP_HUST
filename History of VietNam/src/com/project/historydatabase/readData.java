package com.project.historydatabase;

public class readData {
	public ObservableList<T> FromJsonToArray(String filePath, Class<T> objectType) throws IOException {
        // String carInfoJson = new String(Files.readAllBytes(Paths.get(filePath)));
        Gson gson = new Gson();
        List<T> objects = null;
        Type listType = TypeToken.getParameterized(List.class, objectType).getType();
        objects = gson.fromJson(new FileReader(filePath), listType);
        ObservableList<T> result = FXCollections.observableList(objects);
        return result;
    }
}
