package server;

import common.util.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.commands.*;
import server.handler.CommandHandler;
import server.managers.CommandManager;
import server.managers.LoadManager;
import server.network.TCPDatagramServer;
import server.repo.CityRepository;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Серверная часть приложения.
 */
public class ServerApp {
    /**
     * The constant PORT.
     */
    public static final int PORT = 13579;

    /**
     * The constant logger.
     */
    public static Logger logger = LoggerFactory.getLogger("ServerLogger");

    /**
     * The entry point of application.
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            args = new String[]{"App.java.json"};
            var loadManager = new LoadManager(args[0]);
            var repository = new CityRepository(loadManager);
            logger.info("Сервер запущен");

            Runtime.getRuntime().addShutdownHook(new Thread(repository::save));

            //Проверка аргументов командной строки
            if (args.length == 0) {
                System.out.println("Введите имя загружаемого файла как аргумент командной строки");
                System.exit(1);
            }

            // Проверка доступности порта
            if (!isPortAvailable(PORT)) {
                logger.error("Порт {} уже занят", PORT);
                System.exit(3);
            }

            // Валидация данных
            if (!repository.validateAll()) {
                logger.error("Невалидные продукты в загруженном файле!");
                System.exit(2);
            }

            var commandManager = new CommandManager() {{
                register(Commands.ADD, new Add(repository));
                register(Commands.CLEAR, new Clear(repository));
                register(Commands.EXECUTE_SCRIPT, new ExecuteScript(repository));
                register(Commands.EXIT, new Exit(repository));
                register(Commands.HEAD, new Head(repository));
                register(Commands.HELP, new Help(this));
                register(Commands.INFO, new Info(repository));
                register(Commands.REMOVE_BY_ID, new RemoveById(repository));
                register(Commands.SHOW, new Show(repository));
                register(Commands.TAIL, new Tail(repository));
                register(Commands.UPDATE, new Update(repository));
            }};

            try {
                var server = new TCPDatagramServer(InetAddress.getLocalHost(), PORT, new CommandHandler(commandManager));
                server.setAfterHook(repository::save);
                server.run();
            } catch (SocketException e) {
                logger.error("Ошибка создания сокета на порту {}", PORT, e);
            } catch (UnknownHostException e) {
                logger.error("Неизвестный хост при инициализации сервера", e);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверяет доступность порта
     * @param port номер порта
     * @return true если порт свободен, false если занят
     */
    public static boolean isPortAvailable(int port) {
        try (ServerSocket socket = new ServerSocket(port)) {
            socket.setReuseAddress(true);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
