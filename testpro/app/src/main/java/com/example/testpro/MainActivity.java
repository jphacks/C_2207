package com.example.testpro;


        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.widget.CalendarView;
        import android.widget.Toast;

        import java.awt.Desktop;
        import java.io.BufferedWriter;
        import java.io.File;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.net.URISyntaxException;
        import java.net.URL;
        import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendar = findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                        String message = year + "/" + (month + 1) + "/" + date;
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
        );

    }





        public static void openTwitter(String[] args) {
            if(args.length<1){
                System.out.println("検索ワードをコマンドライン引数に指定してください");
                return;
            }
            try{
                Desktop desktop=Desktop.getDesktop();
                URL input=new URL("https://twitter.com/search?q=from%3A"+String.join("+",args[0]) + "%20since%3A" +String.join("+",args[1]) + "%20" +String.join("+",args[2]) +"&src=typed_query&f=top");
                BufferedWriter bw=new BufferedWriter(new FileWriter(new File("./search.log"), true));
                bw.write("["+new Date()+"] "+input.toString());
                bw.newLine();
                bw.close();
                desktop.browse(input.toURI());
            }catch(UnsupportedOperationException e){
                System.out.println("この環境ではサポートされていません。");
                e.printStackTrace();

            }catch(URISyntaxException e){
                System.out.println("検索ワードが不正です。");
                e.printStackTrace();
            }catch(IOException e){
                System.out.println("エラーが発生しました");
                e.printStackTrace();
            }
        }
    }

}