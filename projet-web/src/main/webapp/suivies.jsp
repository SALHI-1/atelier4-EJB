<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Notes</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 min-h-screen">

<div class="container mx-auto px-4 py-8 max-w-7xl">

    <!-- Header -->
    <div class="mb-8">
        <h1 class="text-3xl font-bold text-gray-800 mb-2">Gestion des Notes</h1>
        <p class="text-gray-600">Liste des notes et ajout de nouvelles évaluations</p>
    </div>

    <!-- Table Section -->
    <div class="bg-white rounded-lg shadow-md overflow-hidden mb-8">
        <div class="px-6 py-4 bg-gradient-to-r from-blue-500 to-blue-600">
            <h2 class="text-xl font-semibold text-white">Liste des Notes</h2>
        </div>

        <div class="overflow-x-auto">
            <table class="w-full">
                <thead class="bg-gray-50">
                <tr>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Étudiant</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Module</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Note</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Date</th>
                </tr>
                </thead>
                <tbody class="bg-white divide-y divide-gray-200">
                <c:forEach items="${listeSuivies}" var="s">
                    <tr class="hover:bg-gray-50 transition-colors">
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                ${s.etudiant.nom} ${s.etudiant.prenom}
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700">
                                ${s.module.nom_module}
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm">
                                <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-blue-100 text-blue-800">
                                        ${s.note}
                                </span>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                ${s.date_notation}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Form Section -->
    <div class="bg-white rounded-lg shadow-md overflow-hidden">
        <div class="px-6 py-4 bg-gradient-to-r from-green-500 to-green-600">
            <h2 class="text-xl font-semibold text-white">Ajouter une Nouvelle Note</h2>
        </div>

        <form action="suivies" method="post" class="p-6">
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">

                <!-- Étudiant -->
                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">
                        Étudiant <span class="text-red-500">*</span>
                    </label>
                    <select name="id_etudiant" required
                            class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all">
                        <option value="">-- Choisir un étudiant --</option>
                        <c:forEach items="${listeEtudiants}" var="etudiant">
                            <option value="${etudiant.id_etudiant}">
                                    ${etudiant.nom} ${etudiant.prenom}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Module -->
                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">
                        Module <span class="text-red-500">*</span>
                    </label>
                    <select name="id_module" required
                            class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all">
                        <option value="">-- Choisir un module --</option>
                        <c:forEach items="${listeModules}" var="mod">
                            <option value="${listeModules[i].id_module}">
                                    ${listeModules[i].nom_module}
                            </option>
                        </c:forEach>

                    </select>
                </div>

                <!-- Note -->
                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">
                        Note <span class="text-red-500">*</span>
                    </label>
                    <input type="number" step="0.1" name="note" required
                           class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all"
                           placeholder="Ex: 15.5">
                </div>

                <!-- Date -->
                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">
                        Date de notation <span class="text-red-500">*</span>
                    </label>
                    <input type="date" name="date_notation" required
                           class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all">
                </div>
            </div>

            <!-- Submit Button -->
            <div class="mt-6">
                <button type="submit"
                        class="w-full md:w-auto px-6 py-3 bg-green-600 hover:bg-green-700 text-white font-medium rounded-lg shadow-sm transition-colors focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-offset-2">
                    <span class="flex items-center justify-center">
                        <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4"/>
                        </svg>
                        Ajouter la note
                </button>
            </div>
        </form>
    </div>

</div>

</body>
</html>