package com.company;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String fileName = "file.txt";
    static  int indexList = 1;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList <WorkingDay> arr = new ArrayList<WorkingDay>();

    abstract static class Pizzeria{
        String name;
        String address;

    }
    public static class WorkingDay extends Pizzeria{
        Date date;
        Integer countOrders;
        String topPizza;

        public String getPizza() { return this.topPizza; }
        public Integer getCountOrders() { return this.countOrders; }
        public void AddNewData() {
            int day, month;
            do {
                System.out.print("Enter number of month: ");
                month = scanner.nextByte();
            } while (month > 12 || month < 1);
            Calendar calendar = new GregorianCalendar(LocalDate.now().getYear(), month - 1, 1);
            do {
                System.out.println("Maximum DAY:" + calendar.getActualMaximum(calendar.DAY_OF_MONTH));
                System.out.print("Enter day: ");
                day = scanner.nextByte();

            } while (day > calendar.getActualMaximum(calendar.DAY_OF_MONTH) || day < 1);
            calendar = new GregorianCalendar(LocalDate.now().getYear(), month - 1, day);
            date = calendar.getTime();
            try {
                FileOutputStream fos = new FileOutputStream(fileName, true);
                DataOutputStream dos = new DataOutputStream(fos);
                System.out.print("Enter name: ");
                name = scanner.nextLine();
                name = scanner.nextLine();
                System.out.print("Enter address: ");
                address = scanner.nextLine();
                System.out.print("Enter pizza of the day: ");
                topPizza = scanner.nextLine();
                System.out.print("Enter count orders: ");
                countOrders = scanner.nextInt();
                dos.writeUTF(name);
                dos.writeUTF(address);
                dos.writeUTF(new SimpleDateFormat("dd/MM/y", Locale.ENGLISH).format(date.getTime()));
                dos.writeUTF(topPizza);
                dos.write(countOrders);
                dos.close();
                workingDayInfo.add(this);
                scanner.nextLine();
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
        public void EditData() {
            byte lineIndex;
            do {
                do {
                    System.out.print("Choose line to edit\n(1.Name | 2.Address | 3.Date | 4.Count orders | 5.Pizza of the day |)\n(6.Back to choose menu)\nEnter index:");
                    lineIndex = scanner.nextByte();
                } while (lineIndex < 1 || lineIndex > 7);
                switch (lineIndex) {
                    case 1: {
                        System.out.print("Enter surname: ");
                        name = scanner.nextLine();
                        name = scanner.nextLine();
                    }

                    case 2: {
                        System.out.print("Enter genre: ");
                        address = scanner.nextLine();
                        address = scanner.nextLine();
                    }

                    break;
                    case 3: {
                        byte day;
                        byte month;
                        do {
                            System.out.print("Enter number of month: ");
                            month = scanner.nextByte();
                        } while (month > 12 || month < 1);
                        Calendar calendar = new GregorianCalendar(LocalDate.now().getYear(), month - 1, 1);
                        do {
                            System.out.println("Maximum DAY:" + calendar.getActualMaximum(calendar.DAY_OF_MONTH));
                            System.out.print("Enter day: ");
                            day = scanner.nextByte();

                        } while (day > calendar.getActualMaximum(calendar.DAY_OF_MONTH) || day < 1);
                        calendar = new GregorianCalendar(LocalDate.now().getYear(), month - 1, day);
                        date = calendar.getTime();
                    }

                    break;
                    case 4: {
                        System.out.print("Enter count orders: ");
                        countOrders = scanner.nextInt();
                    }

                    break;

                    case 5: {
                        System.out.print("Enter pizza of the day: ");
                        topPizza = scanner.nextLine();
                        topPizza = scanner.nextLine();
                    }

                }
            } while (lineIndex != 6);
        }
        public void Show() {
            System.out.println("Name: " + name + " | Address: " + address + " | Date: " + new SimpleDateFormat("dd/MM/y", Locale.ENGLISH).format(date.getTime()) + " | Count orders: " + countOrders + " | Pizza of the day: " + topPizza);
        }


    }
    static void averageNumberOrders()
    {
        int dayFirst, monthFirst;
        Date dateSecond;
        do {
            System.out.print("Enter number of month: ");
            monthFirst = scanner.nextByte();
        } while (monthFirst > 12 || monthFirst < 1);
        Calendar calendarFirst = new GregorianCalendar(LocalDate.now().getYear(), monthFirst - 1, 1);
        do {
            System.out.println("Maximum DAY:" + calendarFirst.getActualMaximum(calendarFirst.DAY_OF_MONTH));
            System.out.print("Enter day: ");
            dayFirst = scanner.nextByte();

        } while (dayFirst > calendarFirst.getActualMaximum(calendarFirst.DAY_OF_MONTH) || dayFirst < 1);
        calendarFirst = new GregorianCalendar(LocalDate.now().getYear(), monthFirst - 1, dayFirst);
        Date dateFirst = calendarFirst.getTime();
        int daySecond, monthSecond;
        do {
            do {
                System.out.print("Enter number of month (>=" +monthFirst +"): ");
                monthSecond = scanner.nextByte();
            } while (monthSecond > 12 || monthSecond < 1);
        } while (monthSecond < monthFirst);
        Calendar calendarSecond = new GregorianCalendar(LocalDate.now().getYear(), monthSecond - 1, 1);
        boolean flag = true;
        do {
            do {
                System.out.println("Maximum DAY:" + calendarSecond.getActualMaximum(calendarSecond.DAY_OF_MONTH));
                System.out.print("Enter day: ");
                daySecond = scanner.nextByte();
            } while (dayFirst > calendarSecond.getActualMaximum(calendarSecond.DAY_OF_MONTH) || daySecond < 1);
        calendarSecond = new GregorianCalendar(LocalDate.now().getYear(), monthSecond - 1, daySecond);
         dateSecond = calendarSecond.getTime();
            if(dateFirst.after(dateSecond)) {
                flag = false;
            } flag = true;
        } while (!flag);
        int[] array = new int[2];
        for(WorkingDay tmp: workingDayInfo)
        {
            if(tmp.date.after(dateFirst) && tmp.date.before(dateSecond) || tmp.date.equals(dateFirst) || tmp.date.equals(dateSecond))
            {
                array[0]++;
                array[1]+= tmp.countOrders;
            }
        }
        System.out.println("Average orders: " + array[1]/array[0]);

    }
    static ArrayList<WorkingDay> workingDayInfo = new ArrayList<WorkingDay>();
    static void LoadFromFile() {
        workingDayInfo.clear();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            DataInputStream dis = new DataInputStream(fis);
            WorkingDay temp;
            while (dis.available() > 0) {
                temp = new WorkingDay();
                temp.name = dis.readUTF();
                temp.address = dis.readUTF();
                try {
                    temp.date = new SimpleDateFormat("dd/MM/y").parse(dis.readUTF());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                temp.topPizza = dis.readUTF();
                temp.countOrders = dis.read();
                workingDayInfo.add(temp);
            }
            dis.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String enterkey;
        LoadFromFile();
        try {
            do {
                System.out.println("Press 'p' - edit, 'a' - add, 's' - show, 'd' - delete, 'c' - Average numbers of orders for the period, 'm' - Max attendance, 't' - Total number of orders for days with a specific pizza of the day, 'e' - exit");


                enterkey = reader.readLine();


                WorkingDay temp = new WorkingDay();
                switch (enterkey.charAt(0)) {
                    case 'p': {
                        if (workingDayInfo.isEmpty()) {
                            System.out.println("Concert list is empty, add new data.");
                        } else {
                            System.out.println("Info list:");
                            indexList = 1;
                            for (WorkingDay tmpWorkingDay : workingDayInfo) {
                                System.out.print(indexList + ": "); tmpWorkingDay.Show(); indexList++;
                            }
                        }
                        byte index;
                        do {
                            System.out.print("Enter index(" + "from 1 to " + workingDayInfo.size() + ") of pizzeria to edit:");
                            index = scanner.nextByte();
                        } while (index < 1 || index > workingDayInfo.size());
                        workingDayInfo.get(index - 1).EditData();
                    }
                    break;
                    case 's': {
                        if (workingDayInfo.isEmpty()) {
                            System.out.println("Pizzeria list is empty, add new data.");
                        } else {
                            System.out.println("Info list:");
                            indexList = 1;
                            for (WorkingDay tmpWorkingDay : workingDayInfo) {
                                System.out.print(indexList + ": "); tmpWorkingDay.Show(); indexList++;
                            }
                        }
                    }
                    break;
                    case 'a':
                        temp.AddNewData();
                        break;
                    case 'c': {
                        if (workingDayInfo.isEmpty()) {
                            System.out.println("Pizzeria list is empty, add new data.");
                        } else {
                            averageNumberOrders();
                            }
                        }

                    break;
                    case 'm': {
                        if (workingDayInfo.isEmpty()) {
                            System.out.println("Pizzeria list is empty, add new data.");
                        } else {
                            int maxIndex = 0;
                            for (int i = 0; i < workingDayInfo.size(); i++) {
                                if (workingDayInfo.get(maxIndex).countOrders < workingDayInfo.get(i).countOrders) {
                                    maxIndex = i;
                                }
                            }
                            System.out.println("Days with max attendance:");

                            workingDayInfo.get(maxIndex).Show();
                        }
                    }

                    break;
                    case 'd': {
                        if (workingDayInfo.isEmpty()) {
                            System.out.println("Pizzeria list is empty, add new data.");
                        } else {
                            System.out.println("Info list:");
                            for (WorkingDay tmpWorkingDay : workingDayInfo) {
                                tmpWorkingDay.Show();
                            }
                        }
                        byte index;
                        do {
                            System.out.print("Enter index(" + "from 1 to " + workingDayInfo.size() + ") of pizzeria to delete:");
                            index = scanner.nextByte();
                        } while (index < 1 || index > workingDayInfo.size());
                        workingDayInfo.remove(index - 1);
                    }
                    break;

                    case 't': {
                        if (workingDayInfo.isEmpty()) {
                            System.out.println("Pizzeria list is empty, add new data.");
                        } else {
                            System.out.print("Enter pizza:");
                            String enterPizza = scanner.nextLine();
                            int summ = 0;
                            for (int i = 0; i < workingDayInfo.size(); i++) {
                                if (workingDayInfo.get(i).topPizza.equals(enterPizza)) {
                                    summ += workingDayInfo.get(i).countOrders;
                                }
                            }
                            System.out.println("The total number of orders for days with a given pizza of the day: " + summ);
                        }
                    }
                    break;

                }

            } while (enterkey.charAt(0) != 'e');
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
