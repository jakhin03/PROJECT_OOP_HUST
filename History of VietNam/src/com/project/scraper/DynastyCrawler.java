package com.project.scraper;

public class DynastyCrawler{
	public static void crawler() {
        try {
            int check = 0;
            String baseUrl = "https://openedu.vn/Kho-tri-thuc/Tom-luoc-lich-su-Viet-Nam-qua-cac-thoi-dai";
            List<Map<String, Object>> dynastyList = new ArrayList<Map<String, Object>>();
            Document doc;
            doc = Jsoup.connect(baseUrl).get();
            Elements links = doc.select("span[id=\"docs-internal-guid-f0613dd5-7fff-7e8f-3339-7c7b3f121065\"]");
            List<DynastyChild> dynastyChildren = new ArrayList<DynastyChild>();
            List<DynastyChild> dynastyChildren2;
            for (int i = links.size() - 4; i >= 5; i--) {
                Map<String, Object> data = new HashMap<>();
                String dynasty = links.get(i).text();
                if (check == 1) {
                    dynastyChildren.removeAll(dynastyChildren);
                    check = 0;
                }
                if (dynasty.contains(":") == true && i != 30) {
                    DynastyChild dynastyChild = new DynastyChild();
                    String[] output = dynasty.split(":");
                    if (output[0].contains("(") == true) {            // output[0] = Name + Time, output[1] = decription
                        String[] output1 = output[0].split("\\(");    //output1[0] = Name
                        dynastyChild.setName(output1[0]);
                        String[] output2 = output1[1].split("\\)"); //output2 = Time with ")"
                        dynastyChild.setTime(output2[0]);
                        dynastyChild.setDescription(output[1]);
                        dynastyChildren.add(dynastyChild);
                    } else {
                        dynastyChild.setName(output[0]);
                        dynastyChild.setTime("");
                        dynastyChild.setDescription(output[1]);
                        dynastyChildren.add(dynastyChild);
                    }
                } else if (dynasty.contains("(") && i != 106 && i != 92 && i != 78 && i != 30) {
                    String description = links.get(i + 1).text();
                    if (!description.contains(":") && description.contains(".")) {
                        data.put("description", description);
                    } else {
                        data.put("description", "");
                    }
                    String[] output3 = dynasty.split("\\(");
                    data.put("name", output3[0]);
                    String[] output4 = output3[1].split("\\)");
                    data.put("time", output4[0]);
                    dynastyChildren2 = (List<DynastyChild>) ((ArrayList<DynastyChild>) dynastyChildren).clone();
                    data.put("dynasty", dynastyChildren2);
                    check = 1;
                } else if (dynasty.contains(".")) {
                    // skip
                } else {
                    String description = links.get(i + 1).text();
                    if (!description.contains(":") && description.contains(".")) {
                        data.put("description", description);
                    } else {
                        data.put("description", "");
                    }
                    data.put("name", dynasty);
                    data.put("time", "");
                    dynastyChildren2 = (List<DynastyChild>) ((ArrayList<DynastyChild>) dynastyChildren).clone();
                    data.put("dynasty", dynastyChildren2);
                    check = 1;
                }
                if (!data.isEmpty() && i != 40) {
                    dynastyList.add(data);
                }
            }

            Common.saveToJson(dynastyList, "dynasty.json");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
